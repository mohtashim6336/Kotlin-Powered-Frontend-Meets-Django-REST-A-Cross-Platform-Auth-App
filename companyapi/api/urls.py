from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import (CompanyViewSet, register_user, login_user, 
                   forgot_password, get_user_data)

router = DefaultRouter()
router.register(r'companies', CompanyViewSet)

urlpatterns = [
    path('', include(router.urls)),
    path('register/', register_user, name='register'),
    path('login/', login_user, name='login'),
    path('forgot-password/', forgot_password, name='forgot-password'),
    path('user-data/', get_user_data, name='user-data'),
]