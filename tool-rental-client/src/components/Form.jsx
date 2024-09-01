import { useState, useContext } from "react";

import styled from "styled-components";
import Button from "./Button.jsx";
import { ToolsContext } from "./Contexts.jsx";

const Form = ({ props }) => {
    const tools = useContext(ToolsContext);
    const toolBrands = tools.map((tool) => tool.brand);
    const toolTypes = tools.map((tool) => tool.toolType);

    const { actionButtonText, tool, pageTitle, pageSubTitle, handleSubmit } =
        props;

    const [toolType, setToolType] = useState("");
    const [toolBrand, setToolBrand] = useState("");
    const [toolCode, setToolCode] = useState("");
    const [dailyRate, setDailyRate] = useState("");

    const handleSubmitForm = async (e) => {
        e.preventDefault();

        const formData = {
            tool,
        };

        await handleSubmit(formData);
    };

    // const getToolTypes = () => {};

    return (
        <>
            <Form.Page>
                <form onSubmit={handleSubmitForm}>
                    <Form.Body>
                        <Form.Heading>{pageTitle}</Form.Heading>
                        {pageSubTitle ? (
                            <Form.SubHeading>{pageSubTitle}</Form.SubHeading>
                        ) : (
                            ""
                        )}
                        <hr className="divider" />

                        <label htmlFor="tool-type">Tool Type</label>
                        <select
                            onChange={(e) => setToolType(e.target.value)}
                            value={toolType}
                            name="toolType"
                            id="tool-type"
                        >
                            {toolTypes.map((toolType) => (
                                <option
                                    key={toolType.prefix}
                                    value="{toolType.prefix}"
                                >
                                    {toolType.name}
                                </option>
                            ))}
                        </select>

                        <label htmlFor="tool-brand">Tool Brand</label>
                        <select
                            onChange={(e) => setToolBrand(e.target.value)}
                            value={toolBrand}
                            name="toolBrand"
                            id="tool-brand"
                        >
                            {toolBrands.map((toolBrand) => (
                                <option
                                    key={toolBrand.abbreviation}
                                    value="{toolBrand.abbreviation}"
                                >
                                    {toolBrand.name}
                                </option>
                            ))}
                        </select>

                        <label htmlFor="tool-code">Tool Code</label>
                        <input
                            id="tool-code"
                            type="text"
                            value={toolCode}
                            onChange={() => {
                                setToolCode(
                                    toolType &&
                                        toolBrand &&
                                        toolType + toolBrand
                                );
                            }}
                            disabled
                        />

                        <label htmlFor="daily-rate">Daily Rate</label>
                        <input
                            id="daily-rate"
                            type="number"
                            value={dailyRate}
                            onChange={(e) => {
                                setDailyRate(e.target.value);
                            }}
                        />

                        <label htmlFor="weekend-charge">
                            Are you going to charge on weekends?
                        </label>
                        <input
                            id="weekend-charge"
                            type="checkbox"
                            name="weekendCharge"
                            defaultChecked={true}
                        />
                        <label htmlFor="holiday-charge">
                            Are you going to charge on holidays?
                        </label>
                        <input
                            id="holiday-charge"
                            type="checkbox"
                            name="holidayCharge"
                            defaultChecked={true}
                        />
                    </Form.Body>
                    <Form.Actions>
                        <Form.ActionButton>
                            {actionButtonText || pageTitle}
                        </Form.ActionButton>
                    </Form.Actions>
                </form>
            </Form.Page>
        </>
    );
};

const Page = styled.section`
    width: 100%;
    margin-block-start: 15px;

    hr {
        border: 1px solid var(--light-gray-100);
        margin-bottom: 30px;
    }

    @media screen and (min-width: 768px) {
        width: var(--page-width);
        margin-block-start: 40px;
    }
`;

const Heading = styled.div`
    font-size: 18px;
    font-weight: 500;
    line-height: 21.6px;
    color: var(--form-headline-color);
`;
const SubHeading = styled.p`
    font-size: 13px;
    font-weight: 400;
    line-height: 19px;
    color: var(--form-subheadline-color);
`;
const Body = styled.div`
    background-color: #fff;
    box-shadow: 0px 0px 1px 1px var(--light-gray-100);

    border-radius: 4px;
    padding-block: 35px;
    padding-inline: 15px;

    width: 100%;

    @media screen and (min-width: 768px) {
        padding-block: 60px 80px;
        padding-inline: 100px;
    }
`;

const Actions = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    margin-top: 20px;
    padding-inline: 15px;

    font-weight: 400;
    font-size: 14px;
    line-height: 17.92px;

    a {
        color: #556575;
        cursor: pointer;
    }
    a:hover {
        text-decoration: underline;
    }
    @media screen and (min-width: 768px) {
        padding-inline: 0px;
    }
`;

const ActionButton = styled(Button)`
    width: 200px;
`;

Form.Page = Page;
Form.Body = Body;
Form.Heading = Heading;
Form.SubHeading = SubHeading;
Form.Actions = Actions;
Form.ActionButton = ActionButton;

export default Form;
