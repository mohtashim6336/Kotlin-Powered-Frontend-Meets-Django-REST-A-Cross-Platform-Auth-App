# Django-Android Authentication System

Full-stack authentication system with Django REST Framework backend and Android Kotlin frontend.

## Backend Setup

1. Install dependencies:
```bash
<br>
pip install django djangorestframework django-cors-headers djangorestframework-simplejwt

2. Configure Django:
```bash
cd companyapi
python manage.py makemigrations
python manage.py migrate
python manage.py createsuperuser
```

3. Run server:
```bash
python manage.py runserver
```

## Android Setup

1. Open Android Studio
2. Import project
3. Sync Gradle dependencies
4. Update `BASE_URL` in `ApiClient.kt` if needed:
```kotlin
private const val BASE_URL = "http://10.0.2.2:8000/"
```

## Project Structure

### Backend (Django)
- `companyapi/`: Main project directory
- `api/`: App containing authentication endpoints
  - `views.py`: API endpoints
  - `models.py`: Database models
  - `urls.py`: URL routing
  - `serializers.py`: Data serialization

### Android
- `MainActivity.kt`: Main UI and business logic
- `ApiClient.kt`: Retrofit configuration
- `ApiService.kt`: API interface
- `Models.kt`: Data classes
- `activity_main.xml`: UI layout

## API Endpoints

- `POST /api/v1/register/`: User registration
- `POST /api/v1/login/`: User login
- `POST /api/v1/forgot-password/`: Password reset
- `GET /api/v1/user-data/`: Get user profile

## Testing

1. Start Django server
2. Launch Android emulator
3. Run app
4. Try registering/logging in

## Requirements

- Python 3.8+
- Android Studio Arctic Fox+
- Android SDK 24+
- Kotlin 1.8+

## Security Notes

- Update `SECRET_KEY` in `settings.py`
- Disable `DEBUG` in production
- Configure proper `ALLOWED_HOSTS`
- Set up proper email settings for password reset

## Common Issues

1. Connection refused:
   - Verify Django server is running
   - Check `BASE_URL` in `ApiClient.kt`

2. CORS errors:
   - Verify CORS settings in `settings.py`
   - Check Android internet permissions

3. Authentication failures:
- Verify token configuration
- Check authorization headers

