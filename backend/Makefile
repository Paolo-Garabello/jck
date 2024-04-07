SRC_DIR := src
BIN_DIR := bin
LIB_DIR := lib
DB_DIR := db

JAVAC := javac
JAVA := java
JAVADOC := javadoc
SQL := sqlite3
DB_FILE := $(DB_DIR)/database.db
DB_STRUCTURE := $(DB_DIR)/structure.sql
DOC_DIR := docs
CP := $(BIN_DIR):$(LIB_DIR)/*

JAVA_FILES := $(shell find $(SRC_DIR) -name '*.java')
MAIN_CLASS := App

default: build

build:
	@echo "Compiling Java source files..."
	@$(JAVAC) -cp $(CP) -d $(BIN_DIR) $(JAVA_FILES)
	@$(SQL) $(DB_FILE) < $(DB_STRUCTURE)

run:
	@$(JAVA) -cp $(CP) $(MAIN_CLASS)

clean:
	$(RM) -r $(BIN_DIR)/

dbclean:
	rm -f $(DB_FILE)

javadoc:
	@echo "Generating Javadoc..."
	@$(JAVADOC) -d $(DOC_DIR) -classpath $(CP) $(JAVA_FILES)

.PHONY: build run clean dbclean javadoc
