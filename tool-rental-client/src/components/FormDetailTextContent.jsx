import styled from "styled-components";

const FormDetailTextContent = ({ children }) => {
    return (
        <FormDetailTextContentWrapper>{children}</FormDetailTextContentWrapper>
    );
};

const FormDetailTextContentWrapper = styled.div`
    flex: 1 0 auto;
    padding-inline: 33px 30px;
    display: flex;
    flex-direction: column;
    justify-content: center;

    .row {
        margin-block-end: 15px;
    }
    & .heading {
        font-weight: 500;
        font-size: 11px;
        line-height: 1.28;
        color: #a5b2bd;
        text-transform: uppercase;

        .hint {
            text-transform: lowercase;
        }
    }

    & .desc {
        font-weight: 400;
        font-size: 14px;
        line-height: 20px;
        color: #1d2b36;
    }

    .invalid {
        border: 1px solid red;
    }
`;
export default FormDetailTextContent;
