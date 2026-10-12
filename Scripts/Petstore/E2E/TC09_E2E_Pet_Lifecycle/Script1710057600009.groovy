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

Map addVars = [('baseUrl') : GlobalVariable.baseUrl, ('petId') : petId, ('petName') : petName, ('categoryId') : GlobalVariable.categoryId, ('categoryName') : GlobalVariable.categoryName, ('petStatus') : GlobalVariable.petStatus]

Map getVars = [('baseUrl') : GlobalVariable.baseUrl, ('petId') : petId]

Map updateVars = [('baseUrl') : GlobalVariable.baseUrl, ('petId') : petId, ('petName') : updatedName, ('categoryId') : GlobalVariable.categoryId, ('categoryName') : GlobalVariable.categoryName, ('petStatus') : GlobalVariable.updatedStatus]

Map deleteVars = [('baseUrl') : GlobalVariable.baseUrl, ('petId') : petId, ('apiKey') : GlobalVariable.apiKey]

ResponseObject createResponse = WS.sendRequest(findTestObject('Petstore/AddPet', addVars))

WS.verifyResponseStatusCode(createResponse, 200)

def createJson = new JsonSlurper().parseText(createResponse.getResponseText())

assert createJson.name == petName

ResponseObject firstRead = WS.sendRequest(findTestObject('Petstore/GetPet', getVars))

WS.verifyResponseStatusCode(firstRead, 200)

ResponseObject updateResponse = WS.sendRequest(findTestObject('Petstore/UpdatePet', updateVars))

WS.verifyResponseStatusCode(updateResponse, 200)

def updateJson = new JsonSlurper().parseText(updateResponse.getResponseText())

assert updateJson.name == updatedName

assert updateJson.status == GlobalVariable.updatedStatus.toString()

ResponseObject secondRead = WS.sendRequest(findTestObject('Petstore/GetPet', getVars))

WS.verifyResponseStatusCode(secondRead, 200)

def secondReadJson = new JsonSlurper().parseText(secondRead.getResponseText())

assert secondReadJson.name == updatedName

ResponseObject deleteResponse = WS.sendRequest(findTestObject('Petstore/DeletePet', deleteVars))

WS.verifyResponseStatusCode(deleteResponse, 200)

ResponseObject finalRead = WS.sendRequest(findTestObject('Petstore/GetPet', getVars))

WS.verifyResponseStatusCode(finalRead, 404)
