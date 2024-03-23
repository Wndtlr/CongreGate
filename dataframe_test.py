import pandas as pd

people = 170 #test number, 'people' will show current count
time = '7:20 AM' #test time, will show current time

data = {
  "Frank Dining Hall": [80, 130, people],
}

#load data into a DataFrame object:
df = pd.DataFrame(data, index = ["6 AM (avg)", "7 AM (avg)", f"{time} (current)"])

print(df) 