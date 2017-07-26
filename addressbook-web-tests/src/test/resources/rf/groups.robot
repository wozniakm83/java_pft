*** Settings ***
Library    pl.pft.addressbook.rf.AddressbookKeywords
Suite Setup    Init Application Manager
Suite Teardown    Stop Application Manager


*** Test Cases ***
Can Create Group With Valid Data
    ${old_count} =    Get Group Count
    Create Group    Test1    Test1_header    Test1_footer
    ${new_count} =    Get Group Count
    ${expected_count} =    Evaluate    ${old_count} + 1
    Should Be Equal As Integers    ${new_count}    ${expected_count}
