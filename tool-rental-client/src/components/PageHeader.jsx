import styled from "styled-components";
import { Link } from "react-router-dom";
import Button from "./Button";

const StyledPageHeader = styled.section`
    width: var(--page-width-sm);
    display: flex;
    justify-content: space-between;
    align-items: self-end;
    padding-inline: var(--half-of-grid-gap);
    margin-block-start: 40px;

    @media screen and (min-width: 768px) {
        width: var(--page-width);
    }

    .title {
        font-size: 20px;
        font-weight: 500;
        line-height: 25.6px;
        color: var(--page-title-text-dark);
    }
`;

const PageHeader = ({ buttonText, pageTitle }) => {
    return (
        <StyledPageHeader>
            <div className="title">{pageTitle}</div>
            {buttonText && (
                <Link to="admin">
                    <Button>{buttonText}</Button>
                </Link>
            )}
        </StyledPageHeader>
    );
};

export default PageHeader;
