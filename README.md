README
======

Since you're reading this, you have successfully cloned the webdriver-testsuite
repository.

Next steps to get started
-------------------------

1. Install [Apache Maven][maven]
	
	- Debian Linux:

		$ sudo apt-get install maven2

	- Windows: [TBD]

	- Mac OS X: Maven is already installed.

2. Install [Sun Java JRE and JDK][java]

	- Debian Linux:

		$ sudo apt-get install sun-java6-jre sun-java6-jdk

	- Windows: [TBD]

	- Mac OS X: Maven is already installed.

3. Build the project (Make sure, you're in the project root directory, 
   where the <kbd>pom.xml</kbd> file is located)

		$ mvn test

Maven will download all needed dependencies declared in the <kbd>pom.xml</kbd> file,
build the project and execute all present unittests. If all went okay
maven will terminate with the message

	[INFO] BUILD SUCCESSFUL
	
and some runtime statistics.

### Project Setup in your IDE

#### Netbeans

Recommended IDE for developing frontend tests is [NetBeans][nb] because
you just need to open the <kbd>pom.xml</kbd> and nothing else. With Eclipse
the use of Maven is a bit more hassle. For getting the JavaDoc and 
sources of the dependeny open the project explorer in Netbeans and right 
click on the folder <em>Dependencies</em> and just clock the apropriate menue
items.

#### Eclispe

[TBD]

How to write a test
-------------------
In the project view (in Netbeans) you will see the directoy 'Source Packages' 
and 'Test Packages'. In the first one you will only find helper classes 
(e.g. for loading personas, create fixtures etc.). In the latter one you will
find the the unit test classes (either for the helper classes or the frontent
tests). As unit test framework [JUnit][junit] is used. The frontent tests 
itself are contained in the package <kbd>org.cacert.frontendtests</kbd>.
In this package you will add your tests. If you're not familiar with packages in
Java read [this](http://en.wikipedia.org/wiki/Java_package).

Writing a test with JUnit follows nearly the same rules as in [PHPUnit][phpunit] 
(because PHPUnit is a JUnit clone). You also need to extend a base class. But you 
will not extend the JUnits default <kbd>TestCase</kbd> but <kbd>AbstractTestCase</kbd>
from the package <kbd>org.cacert.frontendtests</kbd>. This class provides helper 
methods for creating [Webdriver][webdriver] instances, personas, fixtures or logged 
in sessions.

### Some Java hints:

- Attend the <kbd>@Override</kbd> annotation, if you overwrite a method.
  If you ommit this a compiler warning will be thrown.
- No <kbd>function</kbd> keyword is needed for a method declaration, but
  a return type is required.
- You can ommit <kbd>this</kbd> inside your scope, but keep in mind that
  local variables will shadow class fields.
- Strings are double quoted <kbd>"string"</kbd>, single quotes are characters <kbd>'c'</kbd>!
- Annotations are something similar to interfaces and so need to be imported.
  
An example for a test:

	package org.cacert.frontendtests;
	
	import org.junit.Ignore;
	
	// You do not need to import AbstractTestCase because
	// you are in the same package!
	
	public class MyFirstTest extends AbstractTestCase {
		
		@Override
    	protected void setUp() {
    		// setup stuff
    	}
    	
    	@Override
    	protected void tearDown() {
    		// teardown stuff	
    	}
    	
    	@Ignore("Not ready yet")
    	public void testSomething() {
    		// your test code goes here	
    	}
	}

Some nice examples about annotations just found [here][annos-ger].

[maven]:		http://maven.apache.org/
[java]:			http://www.oracle.com/technetwork/java/javase/downloads/index.html
[nb]:			http://www.netbeans.org
[junit]:		http://www.junit.org/
[annos-ger]:	http://www.frankwestphal.de/JUnit4.0.html
[phpunit]:		https://github.com/sebastianbergmann/phpunit/
[webdriver]:	http://seleniumhq.org/docs/03_webdriver.html
