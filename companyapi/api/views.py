from rest_framework import viewsets, status
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated, AllowAny
from django.contrib.auth import authenticate
from rest_framework_simplejwt.tokens import RefreshToken
from django.core.mail import send_mail
import uuid
from datetime import datetime, timedelta
from django.conf import settings
from django.contrib.auth.models import User
from .models import Company, UserProfile  # Changed this line
from .serializers import UserSerializer, CompanySerializer  # Add this line

class CompanyViewSet(viewsets.ModelViewSet):
    queryset = Company.objects.all()
    serializer_class = CompanySerializer
    permission_classes = [IsAuthenticated]

@api_view(['POST'])
@permission_classes([AllowAny])
def register_user(request):
    serializer = UserSerializer(data=request.data)
    if serializer.is_valid():
        user = serializer.save()
        refresh = RefreshToken.for_user(user)
        return Response({
            'refresh': str(refresh),
            'access': str(refresh.access_token),
        })
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['POST'])
@permission_classes([AllowAny])
def login_user(request):
    username = request.data.get('username')
    password = request.data.get('password')
    user = authenticate(username=username, password=password)
    if user:
        refresh = RefreshToken.for_user(user)
        return Response({
            'refresh': str(refresh),
            'access': str(refresh.access_token),
        })
    return Response({'error': 'Invalid credentials'}, status=status.HTTP_401_UNAUTHORIZED)

@api_view(['POST'])
@permission_classes([AllowAny])
def forgot_password(request):
    email = request.data.get('email')
    try:
        user = User.objects.get(email=email)
        profile = UserProfile.objects.get(user=user)
        reset_token = str(uuid.uuid4())
        profile.reset_token = reset_token
        profile.reset_token_expiry = datetime.now() + timedelta(hours=24)
        profile.save()
        
        # Send email with reset token
        send_mail(
            'Password Reset',
            f'Your password reset token is: {reset_token}',
            settings.EMAIL_HOST_USER,
            [email],
            fail_silently=False,
        )
        return Response({'message': 'Reset token sent to email'})
    except User.DoesNotExist:
        return Response({'error': 'User not found'}, status=status.HTTP_404_NOT_FOUND)

@api_view(['GET'])
@permission_classes([IsAuthenticated])
def get_user_data(request):
    serializer = UserSerializer(request.user)
    return Response(serializer.data)