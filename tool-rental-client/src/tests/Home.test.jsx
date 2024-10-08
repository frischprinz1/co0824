import { expect, test, describe } from "vitest";
import { render, waitFor, screen, fireEvent } from "@testing-library/react";
import { RouterProvider, createMemoryRouter, Navigate } from "react-router-dom";

import Home from "../pages/Home";
import Admin from "../pages/Admin";
import PageLayout from "../components/PageLayout";
import ErrorBoundary from "../components/ErrorBoundary";
import RentalFormDialog from "../components/RentalFormDialog.jsx";
import RentalAgreement from "../pages/RentalAgreement.jsx";

const routeLoaderResponseEmpty = [];
const routeLoaderResponseFull = [
    {
        id: 1,
        toolCode: "JAKD",
        brand: { id: 2, name: "DeWalt", abbreviation: "D" },
        toolType: {
            prefix: "JAK",
            name: "Jackhammer",
            rate: {
                id: "JAK",
                dailyCharge: 2.99,
                hasWeekdayCharge: true,
                hasWeekendCharge: false,
                hasHolidayCharge: false,
            },
        },
    },
    {
        id: 2,
        toolCode: "CHNS",
        brand: { id: 1, name: "Stihl", abbreviation: "S" },
        toolType: {
            prefix: "CHN",
            name: "Chainsaw",
            rate: {
                id: "CHN",
                dailyCharge: 1.49,
                hasWeekdayCharge: true,
                hasWeekendCharge: false,
                hasHolidayCharge: true,
            },
        },
    },
    {
        id: 3,
        toolCode: "LADW",
        brand: { id: 3, name: "Werner", abbreviation: "W" },
        toolType: {
            prefix: "LAD",
            name: "Ladder",
            rate: {
                id: "LAD",
                dailyCharge: 1.99,
                hasWeekdayCharge: true,
                hasWeekendCharge: true,
                hasHolidayCharge: false,
            },
        },
    },
    {
        id: 4,
        toolCode: "JAKR",
        brand: { id: 4, name: "Ridgid", abbreviation: "R" },
        toolType: {
            prefix: "JAK",
            name: "Jackhammer",
            rate: {
                id: "JAK",
                dailyCharge: 2.99,
                hasWeekdayCharge: true,
                hasWeekendCharge: false,
                hasHolidayCharge: false,
            },
        },
    },
];
const routes = [
    {
        path: "/",
        id: "root",
        element: <PageLayout />,
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
];
describe("Home", () => {
    test("renders no tools if api response is empty", async () => {
        routes[0].loader = () => routeLoaderResponseEmpty;
        const router = createMemoryRouter(routes, {
            initialEntries: ["/"],
            initialIndex: 1,
        });

        let globalHeaderElement, noToolsAvailable;
        render(<RouterProvider router={router} />);
        await waitFor(() => {
            globalHeaderElement = screen.getByRole("banner");
            noToolsAvailable = screen.getByText(/No tools found!/);
        });

        expect(globalHeaderElement).toBeInTheDocument();
        expect(noToolsAvailable).toBeInTheDocument();
    });

    test("renders tools from api response", async () => {
        routes[0].loader = () => routeLoaderResponseFull;

        const router = createMemoryRouter(routes, {
            initialEntries: ["/"],
            initialIndex: 1,
        });

        let globalHeaderElement, noToolsAvailable;

        render(<RouterProvider router={router} />);

        await waitFor(() => {
            globalHeaderElement = screen.getByRole("banner");
            noToolsAvailable = screen.queryByText(/No tools found!/);
        });

        expect(globalHeaderElement).toBeInTheDocument();
        expect(noToolsAvailable).toBeNull();
    });

    test("renders correct number of tools from api response", async () => {
        routes[0].loader = () => routeLoaderResponseFull;

        const router = createMemoryRouter(routes, {
            initialEntries: ["/"],
            initialIndex: 1,
        });

        let jackhammerCount, ladder, chainsaw, tools;

        render(<RouterProvider router={router} />);

        await waitFor(() => {
            tools = screen.getAllByRole("toolname", { hidden: true });
            jackhammerCount = screen.getAllByText("Jackhammer");
            chainsaw = screen.getByText("Chainsaw");
            ladder = screen.getByText("Ladder");
        });

        expect(tools.length).toEqual(4);
        expect(jackhammerCount.length).toEqual(2);
        expect(chainsaw).toBeInTheDocument();
        expect(ladder).toBeInTheDocument();
    });

    test("navigates to Admin page on button click", async () => {
        routes[0].loader = () => routeLoaderResponseFull;

        const router = createMemoryRouter(routes, {
            initialEntries: ["/"],
            initialIndex: 1,
        });

        let button;

        render(<RouterProvider router={router} />);

        await waitFor(() => {
            button = screen.getByRole("button", { name: "Add New Tool" });
            expect(button).toBeInTheDocument();
            fireEvent.click(button);
        });

        expect(router.state.location.pathname).toEqual("/tools/admin");
    });
});
