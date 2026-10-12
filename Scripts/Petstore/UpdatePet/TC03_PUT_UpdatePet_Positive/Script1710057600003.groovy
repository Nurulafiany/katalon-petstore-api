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

String updatedName = petName + '_upd'

WS.sendRequest(findTestObject('Petstore/AddPet', [('baseUrl') : GlobalVariable.baseUrl, ('petId') : petId, ('petName') : petName, ('categoryId') : GlobalVariable.categoryId, ('categoryName') : GlobalVariable.categoryName, ('petStatus') : GlobalVariable.petStatus]))

ResponseObject response = WS.sendRequest(findTestObject('Petstore/UpdatePet', [('baseUrl') : GlobalVariable.baseUrl, ('petId') : petId, ('petName') : updatedName, ('categoryId') : GlobalVariable.categoryId, ('categoryName') : GlobalVariable.categoryName, ('petStatus') : GlobalVariable.updatedStatus]))

WS.verifyResponseStatusCode(response, 200)

def json = new JsonSlurper().parseText(response.getResponseText())

assert json.name == updatedName

assert json.status == GlobalVariable.updatedStatus.toString()

WS.sendRequest(findTestObject('Petstore/DeletePet', [('baseUrl') : GlobalVariable.baseUrl, ('petId') : petId, ('apiKey') : GlobalVariable.apiKey]))
