import datetime as dt
import requests




print()
print("Welcome to the weather app! ")
print()

while True:
    try:
        api_key = 'df9f6adc7d89e0757fdee3c72ae7b5eb'
        
        city = input("Search a city or type exit to quit  ")

        if city.lower() == "exit":
            break

        url = f"https://api.openweathermap.org/data/2.5/weather?q={city}&appid={api_key}"



        def kelvin_to_censius_fehrenheit(kelvin):
            celsius = kelvin - 273.15
            fehrenheit = celsius * (9/5) +32
            return celsius, fehrenheit



        response = requests.get(url).json()

        wind_speed = response['wind']['speed']
        temp_kelvin = response['main']['temp']
        temp_celsius, temp_fehrenheit = kelvin_to_censius_fehrenheit(temp_kelvin)
        feels_like_kelvin = response['main']['feels_like']
        feels_like_celsius, feels_like_fehrenheit = kelvin_to_censius_fehrenheit(feels_like_kelvin)
        humidity = response['main']['humidity']
        description = response['weather'][0]['description']
        
        
        sunrise_time = dt.datetime.utcfromtimestamp(response['sys']['sunrise']+response['timezone'])
        sunrise_time_formatted = sunrise_time.strftime("%I:%M %p")

        sunset_time = dt.datetime.utcfromtimestamp(response['sys']['sunset']+response['timezone'])
        sunset_time_formatted = sunset_time.strftime("%I:%M %p")





        print()
        print(f'Tempurature in {city}: {temp_celsius:.2f}°C or {temp_fehrenheit:.2f}°f' ) 
        print()
        print(f'Tempurature in {city} feels like: {feels_like_celsius:.2f}°C or {feels_like_fehrenheit:.2f}°f') 
        print()
        print(f'Humdity in {city}: {humidity}%')
        print()
        print(f'Wind Speed in {city}: {wind_speed}m/s')
        print()
        print(f"General Weather in {city}: is {description}")
        print()
        print(f'Sun Rises in {city} at {sunrise_time_formatted} local time')
        print()
        print(f'Sun Sets in {city} at {sunset_time_formatted} local time')
        print()


    except Exception as e:
        print(f"ERROR: {str(e)}")