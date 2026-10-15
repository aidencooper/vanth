import createClient from "openapi-fetch";
import type { paths as DataPaths, components as DataComponents } from "./generated/data-server";
import type { paths as MediaPaths, components as MediaComponents } from "./generated/media-server";

export type DataSchemas = DataComponents["schemas"];
export type MediaSchemas = MediaComponents["schemas"];

export const createDataClient = (baseUrl: string) => createClient<DataPaths>({ baseUrl});
export const createMediaClient = (baseUrl: string) => createClient<MediaPaths>({ baseUrl });

export type DataClient = ReturnType<typeof createDataClient>;
export type MediaClient = ReturnType<typeof createMediaClient>;