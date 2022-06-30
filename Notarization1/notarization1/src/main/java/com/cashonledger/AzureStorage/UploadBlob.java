package com.cashonledger.AzureStorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;

public class UploadBlob {

    public static void upload() throws IOException {
        String connectStr = "DefaultEndpointsProtocol=https;AccountName=colppunotarizer;AccountKey=11KGh/nYWDw6Iq3qBa8elV8XKx8xTkrQkmbEY2uLqbhGp46cmK2eNR4QAHC4CjSTHa6IRBexsffIDzuON4unXA==;EndpointSuffix=core.windows.net";

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient("original-files");

        // Create a local file in the ./data/ directory for uploading and downloading
        String localPath = "./data/";
        String fileName = "notarization1" + java.util.UUID.randomUUID() + ".txt";
        File localFile = new File(localPath + fileName);

        // Write text to the file
        FileWriter writer = new FileWriter(localPath + fileName, true);
        writer.write("Hello, World!");
        writer.close();

        // Get a reference to a blob
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

        // Upload the blob
        blobClient.uploadFromFile(localPath + fileName);

        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

        // List the blob(s) in the container.
        for (BlobItem blobItem : containerClient.listBlobs()) {
            System.out.println("\t" + blobItem.getName());
        }

    }

}
