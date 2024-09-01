import GlobalHeader from "../components/GlobalHeader";
import PageHeader from "../components/PageHeader";

import AvailableTools from "../components/AvailableTools";
import { Outlet, useRouteLoaderData } from "react-router-dom";
import { ToolsContext } from "../components/Contexts";

const Home = () => {
    const tools = useRouteLoaderData("root");

    return (
        <>
            <GlobalHeader />
            <PageHeader
                pageTitle={"Home"}
                buttonText={"Add New Tool"}
            />

            <ToolsContext.Provider value={tools}>
                <AvailableTools />
                <Outlet />
            </ToolsContext.Provider>
        </>
    );
};

export default Home;
