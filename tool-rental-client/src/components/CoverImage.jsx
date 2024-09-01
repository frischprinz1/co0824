import styled from "styled-components";

const CoverImageWrapper = styled.div`
    width: 241px;

    img {
        width: 100%;
    }
`;

const CoverImage = ({ imageSrc }) => {
    return (
        <CoverImageWrapper>
            <img
                src={imageSrc}
                alt="tool photo"
            />
        </CoverImageWrapper>
    );
};
export default CoverImage;
