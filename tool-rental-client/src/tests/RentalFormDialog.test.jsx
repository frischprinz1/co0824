import { expect, test, describe } from "vitest";
import { render, waitFor, screen, fireEvent } from "@testing-library/react";
import { RouterProvider, createMemoryRouter, Navigate } from "react-router-dom";

import Home from "../pages/Home";
import Admin from "../pages/Admin";
import PageLayout from "../components/PageLayout";
import ErrorBoundary from "../components/ErrorBoundary";
import RentalFormDialog from "../components/RentalFormDialog.jsx";
import RentalAgreement from "../pages/RentalAgreement.jsx";

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
describe("RentalFormDialog", () => {
    test("renders the rental form dialog", async () => {
        routes[0].loader = () => routeLoaderResponseFull;
        const router = createMemoryRouter(routes, {
            initialEntries: ["/tools/JAKD"],
            initialIndex: 1,
        });

        let dialog;
        render(<RouterProvider router={router} />);
        await waitFor(() => {
            dialog = screen.getByRole("dialog");
        });

        expect(dialog).toBeInTheDocument();
    });

    test("submit form if validation passes", async () => {
        routes[0].loader = () => routeLoaderResponseFull;
        const router = createMemoryRouter(routes, {
            initialEntries: ["/tools/JAKD"],
            initialIndex: 1,
        });

        let dateInput, rentalDaysInput, discountInput, checkoutButton;

        render(<RouterProvider router={router} />);
        const checkoutDate = "2015-07-02";
        const rentalDays = 19;
        const discountPercent = 25;

        await waitFor(() => {
            dateInput = screen.getByTitle("Checkout Date Input");
            rentalDaysInput = screen.getByTitle("Rental Days Input");
            discountInput = screen.getByTitle("Discount Percent Input");
            checkoutButton = screen.getByRole("button", { name: "Checkout" });

            fireEvent.change(dateInput, { target: { value: checkoutDate } });
            fireEvent.change(rentalDaysInput, {
                target: { value: rentalDays },
            });
            fireEvent.change(discountInput, {
                target: { value: discountPercent },
            });
            fireEvent.click(checkoutButton);
        });

        expect(dateInput).toBeInTheDocument();
        expect(rentalDaysInput).toBeInTheDocument();
        expect(discountInput).toBeInTheDocument();

        expect(rentalDaysInput.classList.contains("invalid")).toBeFalsy();
    });
    test("does not submit form if rental days validation fails", async () => {
        routes[0].loader = () => routeLoaderResponseFull;
        const router = createMemoryRouter(routes, {
            initialEntries: ["/tools/JAKD"],
            initialIndex: 1,
        });

        let dialog, dateInput, rentalDaysInput, discountInput, checkoutButton;

        render(<RouterProvider router={router} />);
        const checkoutDate = "2015-07-02";
        const rentalDays = 0;
        const discountPercent = 25;

        await waitFor(() => {
            dialog = screen.getByRole("dialog");
            dateInput = screen.getByTitle("Checkout Date Input");
            rentalDaysInput = screen.getByTitle("Rental Days Input");
            discountInput = screen.getByTitle("Discount Percent Input");
            checkoutButton = screen.getByRole("button", { name: "Checkout" });

            fireEvent.change(dateInput, { target: { value: checkoutDate } });
            fireEvent.change(rentalDaysInput, {
                target: { value: rentalDays },
            });
            fireEvent.change(discountInput, {
                target: { value: discountPercent },
            });
            fireEvent.click(checkoutButton);
        });

        expect(dialog).toBeInTheDocument();
        expect(dateInput).toBeInTheDocument();
        expect(rentalDaysInput).toBeInTheDocument();
        expect(discountInput).toBeInTheDocument();

        expect(rentalDaysInput.classList.contains("invalid")).toBeTruthy();
    });
});
