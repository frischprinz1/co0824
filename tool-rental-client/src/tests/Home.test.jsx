import React from "react";
// import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
// import { expect, test, describe } from "vitest";
// import { render, screen } from "../test-utils";
import { render, screen } from "@testing-library/react";

import Home from "../pages/Home";

describe("Home", () => {
    const mockTools = [
        { id: 1, name: "Hammer", description: "A tool for hammering" },
        {
            id: 2,
            name: "Screwdriver",
            description: "A tool for driving screws",
        },
    ];

    test("renders the GlobalHeader component", () => {
        render(
            <BrowserRouter>
                <Home />
            </BrowserRouter>
        );
        const globalHeaderElement = screen.getByRole("banner");
        expect(globalHeaderElement).toBeInTheDocument();
    });

    test("renders the PageHeader component with correct props", () => {
        render(
            <BrowserRouter>
                <Home />
            </BrowserRouter>
        );
        const pageHeaderElement = screen.getByRole("heading", { name: "Home" });
        expect(pageHeaderElement).toBeInTheDocument();
        const buttonElement = screen.getByRole("button", { name: "Admin" });
        expect(buttonElement).toBeInTheDocument();
    });

    test("renders the AvailableTools component with correct context value", () => {
        render(
            <BrowserRouter>
                <ToolsContext.Provider value={mockTools}>
                    <AvailableTools />
                </ToolsContext.Provider>
            </BrowserRouter>
        );
        const toolElements = screen.getAllByRole("listitem");
        expect(toolElements).toHaveLength(mockTools.length);
        toolElements.forEach((toolElement, index) => {
            expect(toolElement).toHaveTextContent(mockTools[index].name);
            expect(toolElement).toHaveTextContent(mockTools[index].description);
        });
    });
});
