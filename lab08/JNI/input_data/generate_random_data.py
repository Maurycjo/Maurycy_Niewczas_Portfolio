import random


def generate_random_data(filename="random_data.txt", size = 500):
    
    with open(filename, 'w') as writer:
        writer.write(str(size))
        writer.write("\n")

        for j in range(0, size):
            for i in range(0, size-1):
                writer.write(str(random.uniform(0, 1000)))
                writer.write(",")
            writer.write(str(random.uniform(0, 1000)))
            writer.write("\n")
    return size


if __name__ =="__main__":

    for j in range(100, 1001, 100):
        for i in range(0, 5):
            generate_random_data(filename=str(j)+"_"+str(i) + ".txt", size=j)
            