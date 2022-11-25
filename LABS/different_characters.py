file = open("LABS/words.txt")

data = file.read().replace("\n","")

s = set(data)

print('Unique letters:',sorted(s))