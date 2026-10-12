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

String petId = String.valueOf(System.currentTimeMillis())

String petName = 'dynPet_' + petId

WS.sendRequest(findTestObject('Petstore/AddPet', [('baseUrl') : GlobalVariable.baseUrl, ('petId') : petId, ('petName') : petName, ('categoryId') : GlobalVariable.categoryId, ('categoryName') : GlobalVariable.categoryName, ('petStatus') : GlobalVariable.petStatus]))

ResponseObject response = WS.sendRequest(findTestObject('Petstore/GetPet', [('baseUrl') : GlobalVariable.baseUrl, ('petId') : petId]))

WS.verifyResponseStatusCode(response, 200)

def json = new JsonSlurper().parseText(response.getResponseText())

assert json.id.toString() == petId

assert json.name == petName

WS.sendRequest(findTestObject('Petstore/DeletePet', [('baseUrl') : GlobalVariable.baseUrl, ('petId') : petId, ('apiKey') : GlobalVariable.apiKey]))
