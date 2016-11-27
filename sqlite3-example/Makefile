CC=g++
DEBUG=
CFLAGS=$(DEBUG) -c
DB_SOURCE=initialize_db.cpp
DB_EXECUTABLE=initialize_db

debug: DEBUG+=-g
debug: clean all

all: $(DB_EXECUTABLE)

$(DB_EXECUTABLE):
	$(CC) $(DEBUG) $@.cpp -lsqlite3 -o $@ -lpthread
	
clean:
	rm -rf *.o $(DB_EXECUTABLE) inventory.db

