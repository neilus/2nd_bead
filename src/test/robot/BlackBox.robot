*** Settings ***
Documentation  Swing GUI tesztelese
Library     SwingLibrary

*** Test Cases ***

Smoke
    Launch Application  hu.elte.progtech1.cwjkl1.RubikTableSwing
    Select Window       HelloWorldSwing
    Close Window        HelloWorldSwing