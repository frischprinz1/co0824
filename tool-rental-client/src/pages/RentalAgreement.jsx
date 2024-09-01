import GlobalHeader from "../components/GlobalHeader";
import PageHeader from "../components/PageHeader";
import styled from "styled-components";

import Button from "../components/Button";
import { Link, useLocation } from "react-router-dom";

const RentalAgreement = () => {
    const { state } = useLocation();

    return (
        <>
            <GlobalHeader />
            <PageHeader pageTitle="Rental Agreement Details" />
            <RentalAgreementWrapper>
                <table>
                    <caption>Rental for: {state.toolType}</caption>

                    <tbody>
                        <tr>
                            <td className="title">Tool code:</td>
                            <td>{state.toolCode}</td>
                        </tr>
                        <tr>
                            <td className="title">Tool type:</td>
                            <td>{state.toolType}</td>
                        </tr>
                        <tr>
                            <td className="title">Tool brand:</td>
                            <td>{state.toolBrand}</td>
                        </tr>
                        <tr>
                            <td className="title">Rental days:</td>
                            <td>{state.rentalDays}</td>
                        </tr>
                        <tr>
                            <td className="title">Check out date:</td>
                            <td>{state.checkoutDate}</td>
                        </tr>
                        <tr>
                            <td className="title">Due Date:</td>
                            <td>{state.dueDate}</td>
                        </tr>
                        <tr>
                            <td className="title">Daily rental charge:</td>
                            <td>{state.dailyRentalCharge}</td>
                        </tr>
                        <tr>
                            <td className="title">Charge days:</td>
                            <td>{state.chargeDays}</td>
                        </tr>
                        <tr>
                            <td className="title">Pre-discount charge:</td>
                            <td>{state.preDiscountCharge}</td>
                        </tr>
                        <tr>
                            <td className="title">Discount percent:</td>
                            <td>{state.discountPercent}</td>
                        </tr>
                        <tr>
                            <td className="title">Discount amount:</td>
                            <td>{state.discountAmount}</td>
                        </tr>
                    </tbody>

                    <tfoot>
                        <tr>
                            <td className="title">Final charge:</td>
                            <td>{state.finalCharge}</td>
                        </tr>
                    </tfoot>
                </table>

                <Link to="/">
                    <Button>OK</Button>
                </Link>
            </RentalAgreementWrapper>
        </>
    );
};

export default RentalAgreement;

const RentalAgreementWrapper = styled.div`
    width: var(--page-width);
    padding-inline: var(--half-of-grid-gap);
    margin-block-start: 1rem;
    table {
        border-collapse: collapse;
        border: 1px solid rgb(140 140 140);
        font-family: sans-serif;
        // font-size: 0.8rem;
        letter-spacing: 1px;
        margin-block-end: 1rem;
    }

    thead,
    tfoot {
        background-color: rgb(213 216 229);
    }

    th,
    td {
        border: 1px solid rgb(160 160 160);
        padding: 8px 10px;
    }

    td:last-of-type {
        text-align: center;
    }

    tbody > tr:nth-of-type(even) {
        background-color: rgb(237 238 242);
    }

    tfoot th {
        text-align: right;
    }

    tfoot td {
        font-weight: bold;
    }
`;
