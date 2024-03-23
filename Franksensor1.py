def sensors_to_data(activation: bool) -> int:
    #just to simulate what will be given to us
    increment = 0
    #sensor 1 then sensor 2: increment = 1
    #sensor 2 then sensor 1: increment = -1
    increment += int(input('Enter: 1, Leave: -1 '))
    return increment

def people_increment(people: int, increment: int, activation: bool)-> int:
    
    while activation:
        people += increment
        return people


people = 0
time = True
while time:
    ask = input('Activated? (y if yes) ')  #for testing
    if ask == 'y':
        activation = True
    else:
        activation = False
    
    while activation:
        increment = sensors_to_data(activation)
        people = people_increment(people, increment, activation)
        print(people)
        ask = input('Activated? (y if yes) ')  #for testing
        if ask == 'y':
            activation = True
        else:
            activation = False