from rest_framework import serializers
from django.contrib.auth.models import User
from .models import Company, UserProfile

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'username', 'email', 'password')
        extra_kwargs = {'password': {'write_only': True}}

    def create(self, validated_data):
        user = User.objects.create_user(**validated_data)
        UserProfile.objects.create(user=user)
        return user

class CompanySerializer(serializers.HyperlinkedModelSerializer):
    company_id = serializers.ReadOnlyField()
    class Meta:
        model = Company
        fields = "__all__"