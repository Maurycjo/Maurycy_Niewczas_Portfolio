import random


def generate_random_data(filename="random_data.txt", min_size = 2000, max_size = 2000):
    size = random.randint(min_size, max_size)

    with open(filename, 'w') as writer:
        writer.write(str(size))
        writer.write("\n")

        for j in range(0, size):
            for i in range(0, size-1):
                writer.write(str(random.uniform(0, 50)))
                writer.write(",")
            writer.write(str(random.uniform(0, 50)))
            writer.write("\n")
    return size


if __name__ =="__main__":
    for i in range(0, 5):
        generate_random_data(filename=str(i) + ".txt")