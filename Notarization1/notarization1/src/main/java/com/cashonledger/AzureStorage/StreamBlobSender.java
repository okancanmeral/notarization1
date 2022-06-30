package com.cashonledger.AzureStorage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.specialized.BlockBlobClient;

public class StreamBlobSender {
    public void connectionStream() {

        // create a blob client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(
                        "https://colppunotarizer.blob.core.windows.net/original-files?sp=r&st=2022-06-23T08:48:27Z&se=2022-06-23T16:48:27Z&spr=https&sv=2021-06-08&sr=c&sig=OnMMxGhj5ddDSPZ9CEn7QC3yPmKwWu3I2vCWkfZoug4%3D")
                .sasToken(
                        "sp=r&st=2022-06-23T08:48:27Z&se=2022-06-23T16:48:27Z&spr=https&sv=2021-06-08&sr=c&sig=OnMMxGhj5ddDSPZ9CEn7QC3yPmKwWu3I2vCWkfZoug4%3D")
                .buildClient();
        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient("original-files");

        BlockBlobClient blockBlobClient = blobContainerClient.getBlobClient("myblockblob").getBlockBlobClient();
        String dataSample = "samples";
        try (ByteArrayInputStream dataStream = new ByteArrayInputStream(dataSample.getBytes())) {
            blockBlobClient.upload(dataStream, dataSample.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            blockBlobClient.downloadStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}