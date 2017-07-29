/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.connector;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.util.HashMap;
import java.util.Map;

public class waveanalyticsIntegrationTest extends ConnectorIntegrationTestBase {

    private Map<String, String> esbRequestHeadersMap = new HashMap<String, String>();
    private Map<String, String> apiRequestHeadersMap = new HashMap<String, String>();
    private String apiUrl;

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        init("waveanalytics-connector-1.0.0");
        esbRequestHeadersMap.put("Accept-Charset", "UTF-8");
        esbRequestHeadersMap.put("Content-Type", "application/json");
        apiUrl = connectorProperties.getProperty("apiUrl");
        String apiEndpointUrl = "https://login.salesforce.com/services/oauth2/token?grant_type=refresh_token&client_id=" + connectorProperties.getProperty("clientId") + "&client_secret=" + connectorProperties.getProperty("clientSecret") + "&refresh_token=" + connectorProperties.getProperty("refreshToken");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndpointUrl, "POST", apiRequestHeadersMap);
        final String accessToken = apiRestResponse.getBody().getString("access_token");
        connectorProperties.put("accessToken", accessToken);
        apiRequestHeadersMap.put("Authorization", "Bearer " + accessToken);
        apiRequestHeadersMap.putAll(esbRequestHeadersMap);
    }

    /*
        Positive test case for getWaveResource with mandatory parameters.

    */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {getWaveResource} integration test with mantatory parameters.")
    public void testGetWaveResourceWithMandatoryParameters() throws Exception {

        esbRequestHeadersMap.put("Action", "urn:getWaveResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getWaveResource_mandatory.json");
        final String apiEndPoint = apiUrl + "/wave";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("lenses"), apiRestResponse.getBody().getString("lenses"));

    }
    
    /*
        Positive test case for getDashboardListResource with mandatory parameters.
    */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {getDashboardListResource} integration test with mantatory parameters.")
    public void testGetDashboardListResourceWithMandatoryParameters() throws Exception {
        esbRequestHeadersMap.put("Action", "urn:getDashboardListResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getDashboardListResource_mandatory.json");
        final String apiEndPoint = apiUrl + "/wave/dashboards";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("dashboards"), apiRestResponse.getBody().getString("dashboards"));
    }

    /*
        Positive test case for getDashboardListResource with optional parameters.
    */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {getDashboardListResource} integration test with optional parameters.")
    public void testGetDashboardListResourceWithOptionalParameters() throws Exception {
        esbRequestHeadersMap.put("Action", "urn:getDashboardListResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getDashboardListResource_optional.json");
        final String apiEndPoint = apiUrl + "/wave/dashboards?&folderId=" + connectorProperties.getProperty
                ("folderId") + "&pageSize=" +
                connectorProperties.getProperty("pageSize") + "&q=" + connectorProperties.getProperty("q") + "&sort=" + connectorProperties.getProperty("sort");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("dashboards"), apiRestResponse.getBody().getString("dashboards"));
    }

    /*
        Positive test case for getDatasetListResource with mandatory parameters.
    */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {getDatasetListResource} integration test with mantatory parameters.")
    public void testGetDatasetListResourceWithMandatoryParameters() throws Exception {
        esbRequestHeadersMap.put("Action", "urn:getDatasetListResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getDatasetListResource_mandatory.json");
        final String apiEndPoint = apiUrl + "/wave/datasets";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("datasets"), apiRestResponse.getBody().getString("datasets"));
    }

    /*
        Positive test case for getDatasetListResource with optional parameters.
    */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {getDatasetListResource} integration test with optional parameters.")
    public void testGetDatasetListResourceWithOptionalParameters() throws Exception {
        esbRequestHeadersMap.put("Action", "urn:getDatasetListResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getDatasetListResource_optional.json");
        final String apiEndPoint = apiUrl + "/wave/datasets?&folderId=" + connectorProperties.getProperty("folderId") + "&hasCurrentOnly=" + connectorProperties.getProperty("hasCurrentOnly") + "&pageSize=" + connectorProperties.getProperty("pageSize") + "&q=" + connectorProperties.getProperty("q") + "&sort=" + connectorProperties.getProperty("sort");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("datasets"), apiRestResponse.getBody().getString("datasets"));
    }

    /*
       Positive test case for getDatasetResource with mandatory parameters.
    */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {getDatasetResource} integration test with mantatory parameters.")
    public void testGetDatasetResourceWithMandatoryParameters() throws Exception {
        esbRequestHeadersMap.put("Action", "urn:getDatasetResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getDatasetResource_mandatory.json");
        final String apiEndPoint = apiUrl + "/wave/datasets/" + connectorProperties.getProperty("datasetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("currentVersionId"), apiRestResponse.getBody().getString("currentVersionId"));
    }

    /*
       Positive test case for updateDatasetResource with mandatory parameters.
    */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {updateDatasetResource} integration test with mantatory parameters.")
    public void testUpdateDatasetResourceWithMandatoryParameters() throws Exception {
        esbRequestHeadersMap.put("Action", "urn:updateDatasetResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_updateDatasetResource_mandatory.json");
        final String apiEndPoint = apiUrl + "/wave/datasets/" + connectorProperties.getProperty("datasetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("currentVersionId"), apiRestResponse.getBody().getString("currentVersionId"));
    }

    /*
        Positive test case for getVersionResource with mandatory parameters.
    */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {getVersionResource} integration test with mantatory parameters.")
    public void testGetVersionResourceWithMandatoryParameters() throws Exception {
        esbRequestHeadersMap.put("Action", "urn:getVersionResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getVersionResource_mandatory.json");
        final String apiEndPoint = apiUrl + "/wave/datasets/" + connectorProperties.getProperty("datasetId") + "/versions/" + connectorProperties.getProperty("versionId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("url"), apiRestResponse.getBody().getString("url"));
    }

    /*
       Positive test case for getVersionListResource with mandatory parameters.
    */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {getVersionListResource} integration test with mantatory parameters.")
    public void testGetVersionListResourceWithMandatoryParameters() throws Exception {
        esbRequestHeadersMap.put("Action", "urn:getVersionsListResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getVersionListResource_mandatory.json");
        final String apiEndPoint = apiUrl + "/wave/datasets/" + connectorProperties.getProperty("datasetId") + "/versions";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("versions"), apiRestResponse.getBody().getString("versions"));
    }

    /*
       Positive test case for getXmdListResource with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {getXmdListResource} integration test with mantatory parameters.")
    public void testGetXmdListResourceWithMandatoryParameters() throws Exception {
        esbRequestHeadersMap.put("Action", "urn:getXmdListResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getXmdListResource_mandatory.json");
        final String apiEndPoint = apiUrl + "/wave/datasets/" + connectorProperties.getProperty("datasetId") + "/versions/" + connectorProperties.getProperty("versionId") + "/xmds";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("url"), apiRestResponse.getBody().getString("url"));
    }

    /*
       Positive test case for getXmdResource with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {getXmdResource} integration test with mantatory parameters.")
    public void testGetXmdResourceWithMandatoryParameters() throws Exception {
        esbRequestHeadersMap.put("Action", "urn:getXmdResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getXmdResource_mandatory.json");
        final String apiEndPoint = apiUrl + "/wave/datasets/" + connectorProperties.getProperty("datasetId") + "/versions/" + connectorProperties.getProperty("versionId") + "/xmds/" + connectorProperties.getProperty("userOnly");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("url"), apiRestResponse.getBody().getString("url"));
    }

    /*
        Positive test case for deleteDatasetResource with mandatory parameters.
    */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "waveanalytics {deleteDatasetResource} integration test with mantatory parameters.")
    public void testDeleteDatasetListResourceWithMandatoryParameters() throws Exception {
        esbRequestHeadersMap.put("Action", "urn:deleteDatasetResource");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap,
                "esb_deleteDatasetResource_mandatory.json");
        final String apiEndPoint = apiUrl + "/wave/datasets/" + connectorProperties.getProperty("datasetId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 204);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 204);
    }


}