import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";

import {
    createBrowserRouter,
    Navigate,
    RouterProvider,
} from "react-router-dom";
import ErrorBoundary from "./components/ErrorBoundary";
import PageLayout from "./components/PageLayout.jsx";
import Home from "./pages/Home.jsx";
import Admin from "./pages/Admin.jsx";
import RentalFormDialog from "./components/RentalFormDialog.jsx";
import RentalAgreement from "./pages/RentalAgreement.jsx";

const API_URL = "http://localhost:8080";

const router = createBrowserRouter([
    {
        path: "/",
        element: <PageLayout />,
        id: "root",
        loader: async ({ request, params }) => {
            let tools = null;
            try {
                const tools_url = `${API_URL}/tools/list`;
                const response = await fetch(tools_url);
                tools = await response.json();
            } catch (err) {
                console.log("Could not fetch tools list", err.message);
            }
            return tools;
        },
        errorElement: <ErrorBoundary />,
        children: [
            {
                index: true,
                element: (
                    <Navigate
                        replace
                        to="tools"
                    />
                ),
            },
            {
                path: "tools",
                element: <Home />,
                children: [
                    {
                        path: ":toolCode",
                        element: <RentalFormDialog />,
                    },
                ],
            },

            {
                path: "/tools/admin",
                element: <Admin />,
            },
            {
                path: "/tools/view-rental",
                element: <RentalAgreement />,
            },
        ],
    },
]);

createRoot(document.getElementById("root")).render(
    <StrictMode>
        <RouterProvider router={router} />
    </StrictMode>
);
