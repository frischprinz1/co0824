import styled from "styled-components";
import Button from "./Button";

const ToolDetail = () => {
    return <></>;
};
const Header = ({ children }) => {
    return <ModalHeader>{children}</ModalHeader>;
};
ToolDetail.Header = Header;

const Content = ({ children }) => {
    return <ModalContent>{children}</ModalContent>;
};
ToolDetail.Content = Content;

const Actions = ({ onSubmit }) => {
    return (
        <ModalActions>
            <ModalButton onClick={onSubmit}>Checkout</ModalButton>
        </ModalActions>
    );
};
ToolDetail.Actions = Actions;

const ModalFlex = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-inline: 30px;
    width: 100%;
`;

const ModalHeader = styled(ModalFlex)`
    border-bottom: 1px solid var(--light-gray-100);
    height: 50px;

    & .brand {
        font-size: 16px;
        font-weight: 500;
        line-height: 19.2px;
        color: #1d2b36;
    }

    .close-btn {
        color: #93a1b0;
        cursor: pointer;
    }
`;

const ModalContent = styled(ModalFlex)`
    justify-content: center;
    padding-block: 44px;
    height: 450px;
`;
const ModalActions = styled(ModalFlex)`
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
`;
const ModalButton = styled(Button)`
    width: 100%;
    margin-block-start: 30px;
`;

export default ToolDetail;
