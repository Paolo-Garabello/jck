JAVAC = javac
JAVA = java
SRCDIR = src
BINDIR = bin
LIBDIR = lib
MAINCLASS = Main
CLASSPATH = $(LIBDIR)/*:$(BINDIR)

.SUFFIXES: .java .class

.java.class:
	$(JAVAC) -cp $(CLASSPATH) -d $(BINDIR) $*.java

CLASSES = \
    $(SRCDIR)/Main.java

default: classes

classes: $(CLASSES:.java=.class)

run:
	$(JAVA) -cp $(CLASSPATH) $(MAINCLASS)

clean:
	$(RM) $(BINDIR)/*.class
