/* eslint-disable react/prop-types */
import * as AlertDialog from "@radix-ui/react-alert-dialog";
import styled, { keyframes } from "styled-components";
import { useState } from "react";
import { Link } from "react-router-dom";

const Alert = ({ props }) => {
    const [open, setOpen] = useState(true);
    const {
        triggerText,
        title,
        description,
        cancelActionText,
        actionText,
        url,
        data,
    } = props;

    return (
        <AlertDialog.Root
            open={open}
            onOpenChange={setOpen}
        >
            <AlertDialogTrigger asChild>
                <span className="Button link">{triggerText}</span>
            </AlertDialogTrigger>
            <AlertDialog.Portal>
                <AlertDialogOverlay />
                <AlertDialogTitle>{title}</AlertDialogTitle>
                <AlertDialogContent aria-describedby={undefined}>
                    <AlertDialogDescription>
                        {description}
                    </AlertDialogDescription>
                    <Flex>
                        {cancelActionText && (
                            <AlertDialogCancel asChild>
                                <button className="Cancel">
                                    {cancelActionText}
                                </button>
                            </AlertDialogCancel>
                        )}
                        <AlertDialogAction asChild>
                            <Link
                                to={url}
                                state={data}
                            >
                                <span className="actionText">{actionText}</span>
                            </Link>
                        </AlertDialogAction>
                    </Flex>
                </AlertDialogContent>
            </AlertDialog.Portal>
        </AlertDialog.Root>
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
const Flex = styled.div`
    display: flex;
    justify-content: center;

    font-size: 14px;
    font-weight: 500;
    line-height: 17px;
    color: #405261;
`;

const AlertDialogTrigger = styled(AlertDialog.Trigger)`
    // all: unset;
    font-weight: 400;
    font-size: 14px;
    line-height: 17.92px;

    color: #556575;
    cursor: pointer;

    &:hover {
        text-decoration: underline;
    }
`;
const AlertDialogCancel = styled(AlertDialog.Cancel)`
    width: 123px;
    height: 40px;
    border-radius: 3px;
    border: none;
    padding-block: 0px;
    background-color: var(--cobalt-blue);
    font-size: 14px;
    line-height: 17px;
    text-align: center;
    cursor: pointer;

    background: none;
    margin-inline-end: 15px;
    color: #405261;
    border: 1px solid var(--light-gray-200);
`;

const AlertDialogAction = styled(AlertDialog.Action)`
    width: 123px;
    height: 40px;
    border-radius: 3px;
    border: none;
    padding-block: 0px;
    background-color: var(--cobalt-blue);
    font-size: 14px;
    line-height: 17px;
    text-align: center;
    color: #fff;
    cursor: pointer;

    display: flex;
    align-items: center;
    justify-content: center;

    span.actionText {
        color: #fff;
        text-decoration: none;
    }
`;

const AlertDialogTitle = styled(AlertDialog.Title)``;
const AlertDialogDescription = styled(AlertDialog.Description)`
    margin-bottom: 20px;
    font-size: 18px;
    font-weight: 500;
    line-height: 21.6px;
    color: #1d2b36;
    text-align: center;
`;

const AlertDialogContent = styled(AlertDialog.Content)`
    display: flex;
    flex-direction: column;
    justify-content: center;
    background-color: white;
    color: #405261;
    border-radius: 6px;
    box-shadow: hsl(206 22% 7% / 35%) 0px 10px 38px -10px,
        hsl(206 22% 7% / 20%) 0px 10px 20px -15px;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 440px;
    height: 219px;
    padding: 35px;
    animation: ${contentShow} 150ms cubic-bezier(0.16, 1, 0.3, 1);
`;

const AlertDialogOverlay = styled(AlertDialog.Overlay)`
    background-color: rgba(3, 64, 119, 0.8);
    position: fixed;
    inset: 0;
    animation: ${overlayShow} 150ms cubic-bezier(0.16, 1, 0.3, 1);
`;

export default Alert;
