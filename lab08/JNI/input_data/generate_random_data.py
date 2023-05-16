import random


def generate_random_data(filename="random_data.txt", min_size = 10, max_size = 15):
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
    size = generate_random_data(filename="1.txt")
    generate_random_data(filename="2.txt", min_size=size, max_size=size)

    size = generate_random_data(filename="3.txt")
    generate_random_data(filename="4.txt", min_size=size, max_size=size)