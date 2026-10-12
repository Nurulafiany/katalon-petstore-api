import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

ResponseObject response = WS.sendRequest(findTestObject('Petstore/UpdatePetInvalid', [('baseUrl') : GlobalVariable.baseUrl]))

WS.verifyResponseStatusCode(response, 400)

def json = new JsonSlurper().parseText(response.getResponseText())

assert json.message == 'bad input'
