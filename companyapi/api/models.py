# api/models.py
from django.db import models
from django.contrib.auth.models import User

class Company(models.Model):
    company_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=50)
    location = models.CharField(max_length=50)
    about = models.TextField()
    type = models.CharField(max_length=100, choices=(
        ('IT', 'IT'),
        ('Non IT', 'Non IT'),
        ("Mobiles Phones", 'Mobile Phones')
    ))
    added_date = models.DateTimeField(auto_now=True)
    active = models.BooleanField(default=True)

class UserProfile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    reset_token = models.CharField(max_length=100, null=True, blank=True)
    reset_token_expiry = models.DateTimeField(null=True, blank=True)