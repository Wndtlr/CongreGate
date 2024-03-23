import pandas as pd

people = 100 #test number, 'people' will show current count
time = '7:20 AM' #test time, will show current time

data = {
  "Frank Dining Hall": [people],
}

#load data into a DataFrame object:
df = pd.DataFrame(data, index = [f"{time}   "])

print(df) 

print()

#placeholder avgs, will update based on data from day
avg6am = 30
avg7am = 70 

data = {
  "Frank Dining Hall": [avg6am, avg7am],
}

#load data into a DataFrame object:
df = pd.DataFrame(data, index = ["6 AM (avg)", "7 AM (avg)"])

print(df) 