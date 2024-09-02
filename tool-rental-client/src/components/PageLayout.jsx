import { Outlet } from "react-router-dom";
import styled from "styled-components";

const PageLayout = () => {
    return (
        <StyledPageLayout>
            <Outlet />
        </StyledPageLayout>
    );
};

const StyledPageLayout = styled.div`
    display: grid;
    place-items: center;
    width: 100%;
    padding-bottom: 50px;

    --page-width: 975px;
    --page-width-sm: 375px;
    --global-padding: 12.5px;

    --placholder-width: 195px;
    --figma-grid-gap: 25px;
    --half-of-grid-gap: calc(var(--figma-grid-gap) / 2);
    --form-headline-color: #131b24;
    --form-subheadline-color: #314351;
`;

export default PageLayout;
