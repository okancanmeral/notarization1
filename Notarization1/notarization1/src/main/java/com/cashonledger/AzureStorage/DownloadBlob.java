package com.cashonledger.AzureStorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;

public class DownloadBlob {

    public static void download() throws IOException {
        String connectStr = "DefaultEndpointsProtocol=https;AccountName=colppunotarizer;AccountKey=11KGh/nYWDw6Iq3qBa8elV8XKx8xTkrQkmbEY2uLqbhGp46cmK2eNR4QAHC4CjSTHa6IRBexsffIDzuON4unXA==;EndpointSuffix=core.windows.net";

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("original-files");

        // Create a local file in the ./data/ directory for uploading and downloading
        String localPath = "./data/";
        String fileName = "notarization1" + java.util.UUID.randomUUID() + ".txt";
        File localFile = new File(localPath + fileName);

        // Get a reference to a blob
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

        // Download the blob to a local file
        // Append the string "DOWNLOAD" before the .txt extension so that you can see
        // both files.
        String downloadFileName = fileName.replace(".txt", "DOWNLOAD.txt");
        File downloadedFile = new File(localPath + downloadFileName);

        System.out.println("\nDownloading blob to\n\t " + localPath + downloadFileName);

        blobClient.downloadToFile(localPath + downloadFileName);

    }

}
