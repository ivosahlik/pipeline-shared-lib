#!/usr/bin/env groovy

/**
 * Get version snapshot/release from Nexus
 * @param NEXUS_URL url for nexus
 * @param REPOSITORY_NAME repository
 * @param ARTIFACT_GROUP
 * @param ARTIFACT_NAME
 * @return artifact list
 */
def static getNexusArtifactList(nexusUrl, repositoryName, artifactGroup, artifactName) {
	def response = httpRequest url: "${nexusUrl}/service/rest/v1/search?repository=${repositoryName}&group=${artifactGroup}&name=${artifactName}",
			validResponseCodes: "200,400,404"
	if (response.status == 200) {
		def responseJson = readJSON text: response.content
		println(responseJson.items.version.collect())
		return new ArrayList<String>(responseJson.items.version.collect())
	} else {
		return []
	}
}
