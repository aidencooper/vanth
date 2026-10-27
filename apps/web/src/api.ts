import { createDataClient, createMediaClient } from "@vanth/api-client";

export const data = createDataClient("/data")
export const media = createMediaClient("/media")