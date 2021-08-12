# java-wicket-testing
Code for various wicket testing exceptions
This project devlopment environment was : Netbeans 11, wicket 9.3.0, Payara 5.201
This project was created first to test Modal Window.
Some code related to exception handling is added to illustrate StalePageException handling.

Follow the steps below to see StalePageException being handled
1. Run the program.
2. Click on one of the buttons, modal window will be displayed. Respond to Modal window - clicking 'Submit' or 'Cancel'.
3. Now right click on the tab, and choose 'Duplicate' option.
4. New tab will be displayed.
5. Choose original tab, and click on one of the button.
6. StalePageException is thrown.

However if page is duplicated before clicking on any button, this error is not thrown.
