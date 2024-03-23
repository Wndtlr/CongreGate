import pandas as pd
import datetime

people = 100 #test number, variable 'people' will show current count

def capacity_calc(people:int) -> str:
  if people <= 50:
    return 'Low Capacity'
  elif people > 50 and people <= 100:
    return 'Medium Capacity'
  elif people > 100 and people <= 150:
    return 'High Capacity'
  else:
    return 'Packed'
  
capacity = capacity_calc(people)

def get_time() -> str:
  now = datetime.datetime.now() 
  if now.hour == 0:
    standard_hour = 12
    AM_PM = 'AM'
  elif now.hour > 12:
    standard_hour = now.hour - 12
    AM_PM = 'PM'
  elif now.hour == 12:
    standard_hour = 12
    AM_PM = 'PM'
  else:
    standard_hour = now.hour
    AM_PM = 'AM'

  if now.minute < 10:
    standard_minute = '0' + str(now.minute)
  else:
     standard_minute = str(now.minute)

  return str(standard_hour) + ':' + standard_minute + AM_PM

time = get_time()

data = {
  "Frank Dining Hall" : {"Capacity": capacity, "LastUpdate":str(time)},
  # "Location": ["Frank Dining Hall"],
  # "Capacity": [capacity],
  # "LastUpdate": [str(time)],
  # f"{time}": [capacity],
}

dataframe = pd.DataFrame(data)

#dataframe = pd.DataFrame(data, index = ["Frank Dining Hall"])

print(dataframe) 