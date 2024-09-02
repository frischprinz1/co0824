import * as Dialog from "@radix-ui/react-dialog";
import styled, { keyframes } from "styled-components";
import { Cross2Icon } from "@radix-ui/react-icons";
import ToolDetail from "./ToolDetail";
import { ToolsContext } from "./Contexts";
import { useContext, useRef, useState } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import FormTextContent from "./FormDetailTextContent";
import ToolCodeDisplay from "./FormToolCodeDisplay";
import BrandDisplay from "./FormBrandDisplay";
import ToolNameDisplay from "./FormToolNameDisplay";
import RateDisplay from "./FormDailyRateDisplay";
import CoverImage from "./CoverImage";
import SuccessAlert from "./SuccessAlert";
import Alert from "./Alert";
import makePostRequest from "../makePostRequest";

const API_URL = "http://localhost:8080/customer/tools";

const RentalFormDialog = ({ children }) => {
    const [open, setOpen] = useState(true);
    const [rentalDays, setRentalDays] = useState("1");
    const [discountPercent, setDiscountPercent] = useState("");
    const [checkoutDate, setCheckoutDate] = useState("");
    const [status, setStatus] = useState(false);
    const [apiResponse, setApiResponse] = useState({});
    const [description, setDescription] = useState("");
    const [nextUrl, setNextUrl] = useState("");

    const title = "Rent Tool";
    const navigate = useNavigate();
    const dateRef = useRef(null);
    const rentalDaysRef = useRef(null);
    const discountRef = useRef(null);
    const tools = useContext(ToolsContext);
    const { toolCode } = useParams();

    const getTool = () => {
        return tools?.filter((tool) => tool.toolCode === toolCode);
    };
    const [tool] = getTool();
    const { toolType, brand } = tool;
    const imageSrc = `../src/assets/${toolType.prefix}.jpg`;

    const handleSubmit = async () => {
        const { toolCode } = tool;
        const isoCheckoutDate =
            checkoutDate && new Date(checkoutDate).toISOString().split("T")[0];

        const validateInput = () => {
            const invalid = [];
            if (!checkoutDate) {
                invalid.push(dateRef.current);
            }

            if (
                !rentalDays ||
                rentalDays < 1 ||
                !Number.isInteger(Number(rentalDays))
            ) {
                invalid.push(rentalDaysRef.current);
            }

            if (
                !discountPercent ||
                !Number.isInteger(Number(discountPercent)) ||
                discountPercent < 0 ||
                discountPercent > 100
            ) {
                invalid.push(discountRef.current);
            }

            const markInvalidFields = (fields) => {
                fields.forEach((field) => {
                    field.classList.add("invalid");
                });
            };
            invalid.length && markInvalidFields(invalid);
            return !invalid.length;
        };

        const createFormData = () => {
            const formData = new FormData();
            formData.append("toolCode", toolCode);
            formData.append("rentalDays", rentalDays);
            formData.append("discountPercent", discountPercent);
            formData.append("checkoutDate", isoCheckoutDate);

            return formData;
        };

        // make api request with form data
        try {
            if (validateInput()) {
                const result = await makePostRequest(API_URL, createFormData);
                setStatus(true);
                setApiResponse(await result.json());
                setDescription("View Rental Agreement");
                setNextUrl("/tools/view-rental");
            }
        } catch (error) {
            console.log(error.message);
        }
    };

    return (
        <Dialog.Root
            open={open}
            onOpenChange={(open) => {
                setOpen(open);
                navigate("..");
            }}
        >
            <DialogTrigger asChild>
                <div>{children}</div>
            </DialogTrigger>
            <Dialog.Portal onClick={() => console.log("clicked the portal")}>
                <DialogOverlay />
                <DialogContent aria-describedby={undefined}>
                    <DialogTitle>
                        <ToolDetail.Header>
                            <div className="title">{title}</div>
                            <DialogClose
                                className="close-btn"
                                asChild
                            >
                                <div
                                    className="IconButton"
                                    aria-label="Close"
                                >
                                    <Link to={".."}>
                                        <Cross2Icon />
                                    </Link>
                                </div>
                            </DialogClose>
                        </ToolDetail.Header>
                    </DialogTitle>
                    <ToolDetail.Content>
                        <CoverImage imageSrc={imageSrc} />
                        <FormTextContent>
                            <ToolCodeDisplay toolCode={toolCode} />
                            <BrandDisplay brand={brand.name} />
                            <ToolNameDisplay toolName={toolType.name} />
                            <RateDisplay
                                dailyCharge={toolType.rate.dailyCharge}
                            />

                            <div className="row">
                                <div className="heading">
                                    <label htmlFor="checkout-date">
                                        Checkout Date (M/d/yy for ex: 7/2/15)
                                    </label>
                                </div>
                                <input
                                    id="checkout-date"
                                    title="Checkout Date Input"
                                    type="date"
                                    value={checkoutDate}
                                    onChange={(e) =>
                                        setCheckoutDate(e.target.value)
                                    }
                                    ref={dateRef}
                                    required
                                />
                            </div>
                            <div className="row">
                                <div className="heading">
                                    <label htmlFor="rental-days">
                                        Rental Days
                                        <span className="hint">(min: 1)</span>
                                    </label>
                                </div>
                                <input
                                    id="rental-days"
                                    title="Rental Days Input"
                                    type="number"
                                    value={rentalDays}
                                    onChange={(e) =>
                                        setRentalDays(e.target.value)
                                    }
                                    min="1"
                                    ref={rentalDaysRef}
                                    required
                                />
                            </div>
                            <div className="row">
                                <div className="heading">
                                    <label htmlFor="discountPercent">
                                        Discount %
                                        <span className="hint">
                                            (range: 0 - 100)
                                        </span>
                                    </label>
                                </div>
                                <input
                                    id="discountPercent"
                                    title="Discount Percent Input"
                                    type="number"
                                    value={discountPercent}
                                    onChange={(e) =>
                                        setDiscountPercent(e.target.value)
                                    }
                                    min="0"
                                    max="100"
                                    ref={discountRef}
                                    required
                                />
                            </div>
                        </FormTextContent>
                    </ToolDetail.Content>
                    <ToolDetail.Actions onSubmit={handleSubmit} />
                    {status && (
                        <SuccessAlert
                            url={nextUrl}
                            data={apiResponse}
                            description={description}
                            actionText="OK"
                        />
                    )}
                    {/* <Alert
                        url={nextUrl}
                        data={apiResponse}
                        description={description}
                        actionText="OK"
                    /> */}
                </DialogContent>
            </Dialog.Portal>
        </Dialog.Root>
    );
};

const overlayShow = keyframes`
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
`;

const contentShow = keyframes`
  from {
    opacity: 0;
    transform: translate(-50%, -48%) scale(0.96);
  }
  to {
    opacity: 1;
    transform: translate(-50%, -50%) scale(1);
  }
`;
const DialogTrigger = styled(Dialog.Trigger)`
    all: unset;
`;
const DialogOverlay = styled(Dialog.Overlay)`
    background-color: rgba(3, 64, 119, 0.8);
    position: fixed;
    inset: 0;
    animation: ${overlayShow} 150ms cubic-bezier(0.16, 1, 0.3, 1);
`;
const DialogTitle = styled(Dialog.Title)`
    margin: 0;
`;
const DialogClose = styled(Dialog.Close)`
    all: unset;
`;
const DialogContent = styled(Dialog.Content)`
    position: fixed;
    top: 50%;
    left: 50%;
    box-shadow: hsl(206 22% 7% / 35%) 0px 10px 38px -10px,
        hsl(206 22% 7% / 20%) 0px 10px 20px -15px;
    transform: translate(-50%, -50%);

    height: 100%;
    width: 100%;
    border: 1px solid var(--light-gray-100);
    background-color: #fff;

    @media screen and (min-width: 768px) {
        height: 612px;
        width: 680px;
        margin: 0 auto;
        border-radius: 5px;
    }

    animation: ${contentShow} 150ms cubic-bezier(0.16, 1, 0.3, 1);
`;

export default RentalFormDialog;
