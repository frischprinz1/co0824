import styled from "styled-components";
import { forwardRef } from "react";

// we pass className as props to be able to extend and style our own custom components using StyledComponents
const Button = forwardRef((props, forwardedRef) => {
    return (
        <Button.StyledButton
            {...props}
            className={props.className}
            ref={forwardedRef}
        >
            {props.children}
        </Button.StyledButton>
    );
});

const StyledButton = styled.button`
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
`;
Button.StyledButton = StyledButton;

export default Button;
