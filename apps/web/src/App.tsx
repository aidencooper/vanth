import { useQuery } from "@tanstack/react-query";
import { data, media } from "./api";
import type { DataSchemas, MediaSchemas } from "@vanth/api-client";

type DataHealth = DataSchemas["Health"];
type MediaHealth = MediaSchemas["Health"];

const fetchDataHealth = async () => {
    const { data: health, error } = await data.GET("/api/v1/health");
    if(error || !health) throw new Error("Data server unreachable");
    return health;
}

const fetchMediaHealth = async () => {
    const { data: health, error } = await media.GET("/api/v1/health");
    if(error || !health) throw new Error("Media server unreachable");
    return health;
}

function ServerStatus({ name, fetchHealth }: { name: string; fetchHealth: () => Promise<DataHealth | MediaHealth> }) {
    const { data: health, isPending, isError } = useQuery({
        queryKey: ["health", name],
        queryFn: fetchHealth,
        retry: 1,
        refetchInterval: 10_000,
    });

    let status = "checking...";
    if(isError) status = "unreachable";
    else if(!isPending) status = `${health.status} · v${health.version}`;

    return (
        <li>
            <strong>{name}</strong>: {status}
        </li>
    )
}

export default function App() {
    return (
        <main>
            <h1>Vanth</h1>
            <ul>
                <ServerStatus name="Data Server" fetchHealth={fetchDataHealth} />
                <ServerStatus name="Media Server" fetchHealth={fetchMediaHealth} />
            </ul>
        </main>
    )
}