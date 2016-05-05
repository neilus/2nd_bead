*** Settings ***
Documentation  Swing GUI tesztelese
Library        SwingLibrary

Test Setup      Startup Application
Test Teardown   Close Window    ${MainWindow}

*** Variables ***
${App}          hu.elte.progtech1.cwjkl1.Main
${MainWindow}   Rubik Table Game
${dimSpinner}   Dimension Spinner
${dimSlider}    Dimension Slider
${newGameBtn}   Start New Game

*** Test Cases ***
Smoke
    [Documentation]  Körbe néznék, mi van itt
    Button Should Exist     ${newGameBtn}
    Spinner Should Exist    ${dimspinner}
    Slider Should Exist     ${dimSlider}
Slider
    [Documentation]  Slider mozgatása és megnézni a dimenzió értékét
    Log To Console          \nRandom Number: ${randomInt}
    Set Slider Value    ${dimSlider}    ${randomInt}
    ${dimValue} =   Get Spinner Value   ${dimSpinner}
    Should Be Equal As Integers      ${randomInt}      ${dimValue}

Increase Dimension by Random
    [Documentation]  Increase the dimension trough the spinner
    Log To Console          \nRandom Number: ${randomInt}
    Increase Spinner Value  ${dimSpinner}   ${randomInt}
    ${spinnerVal} =     Get Spinner Value   ${dimSpinner}
    ${sliderVal} =      Get Slider Value    ${dimSlider}
    Should Be Equal As Integers     ${spinnerVal}   ${sliderVal}

Decrease Dimension by Random
    [Documentation]  Decrease the dimension trough the spinner
    Log To Console          \nRandom Number: ${randomInt}
    Decrease Spinner Value  ${dimSpinner}   ${randomInt}
    ${spinnerVal} =     Get Spinner Value   ${dimSpinner}
    ${sliderVal} =      Get Slider Value    ${dimSlider}
    Should Be Equal As Integers     ${spinnerVal}   ${sliderVal}

*** Keywords ***
Startup Application
    Set Jemmy Dispatch Model     ROBOT
    Launch Application           ${App}
    Select Window                ${MainWindow}
    ${randomInt}=   Evaluate    random.randint(0, 32)   modules=random
    Set Suite Variable  ${randomInt}
