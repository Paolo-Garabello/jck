SRC_DIR := src
BIN_DIR := bin
LIB_DIR := lib

JAVAC := javac
JAVA := java
CP := $(BIN_DIR):$(LIB_DIR)/*

JAVA_FILES := $(wildcard $(SRC_DIR)/*.java)
MAIN_CLASS := App

default: build

build:
	@echo "Compiling Java source files..."
	@$(JAVAC) -cp $(CP) -d $(BIN_DIR) $(JAVA_FILES)

run:
	@$(JAVA) -cp $(CP) $(MAIN_CLASS)

clean:
	$(RM) -r $(BIN_DIR)/

.PHONY: build run clean
