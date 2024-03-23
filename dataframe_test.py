import pandas as pd
import datetime
import pytz

people = 100 #test number, 'people' will show current count
now = datetime.datetime.now() 

if now.hour == 0:
    standard_hour = 12
    AM_PM = 'AM'
elif now.hour > 12:
    standard_hour = now.hour - 12
    AM_PM = 'PM'
else:
    standard_hour = 12
    AM_PM = 'PM'

if now.minute < 10:
    standard_minute = '0' + str(now.minute)
else:
    standard_minute = str(now.minute)

time = str(standard_hour) + ':' + standard_minute + AM_PM

data = {
  "Frank Dining Hall": [people],
}

df = pd.DataFrame(data, index = [f"{time}"])

print(df) 