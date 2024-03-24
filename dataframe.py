import pandas as pd
import datetime

people = 0
increment = 0 #will receive increment from hardware software

def people_count(people: int, increment: int) -> int:
  people += increment
  return people

def capacity_calc(people:int) -> str:
  if people <= 50:
    return 'Low Capacity'
  elif people > 50 and people <= 100:
    return 'Medium Capacity'
  elif people > 100 and people <= 150:
    return 'High Capacity'
  else:
    return 'Packed'

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

people = people_count(people, increment)

people = 100 #test number, variable 'people' will show current count

capacity = capacity_calc(people)

time = get_time()

data = {
  "Frank Dining Hall" : {"Capacity": capacity, "Last Update":str(time)},
  "Coop" : {"Capacity": capacity, "Last Update":str(time)},
  # "Location": ["Frank Dining Hall"],
  # "Capacity": [capacity],
  # "LastUpdate": [str(time)],
  # f"{time}": [capacity],
}

dataframe = pd.DataFrame(data).transpose()

dataframe.to_csv("Frankfully/congregate/src/main/resources/data/df.csv")

print(dataframe) 