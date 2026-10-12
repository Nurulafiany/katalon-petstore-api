<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description>Update pet with malformed JSON body (negative)</description>
   <name>UpdatePetInvalid</name>
   <tag></tag>
   <elementGuidId>7301B413-7663-42F2-A13F-1ED5175B7E8E</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent>{&quot;text&quot;: &quot;{bad json&quot;, &quot;contentType&quot;: &quot;application/json&quot;, &quot;charset&quot;: &quot;UTF-8&quot;}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>PUT</restRequestMethod>
   <restUrl>${baseUrl}/pet</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>GlobalVariable.baseUrl</defaultValue>
      <description></description>
      <id>949646AD-AD19-416C-9B37-AA0E0AC5D780</id>
      <masked>false</masked>
      <name>baseUrl</name>
   </variables>
   <verificationScript></verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
