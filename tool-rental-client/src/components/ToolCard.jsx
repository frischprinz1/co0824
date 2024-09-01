import { useContext } from "react";
import styled from "styled-components";
import { ToolsContext } from "../components/Contexts";

const ToolCard = ({ code }) => {
    const tools = useContext(ToolsContext);
    const tool = tools.find((tool) => tool.toolCode === code);
    const { toolType, toolCode, brand } = tool;
    const imageSrc = `./src/assets/${toolType.prefix}.jpg`;

    return (
        <StyledToolPlaceholder>
            <div
                id="details"
                className="tool"
            >
                <div className="thumbnail">
                    <ToolCard.CoverImage imageSrc={imageSrc} />
                </div>
                <div className="details">
                    <p className="tool-name">{toolType.name}</p>
                    <p className="tool-code">{toolCode}</p>
                    <p className="tool-brand">{brand.name}</p>
                    <p className="tool-rate">${toolType.rate.dailyCharge}</p>
                </div>
            </div>
        </StyledToolPlaceholder>
    );
};

const CoverImage = ({ imageSrc }) => {
    return (
        <CoverImageWrapper>
            <img
                src={imageSrc}
                alt="tool cover"
            />
        </CoverImageWrapper>
    );
};

ToolCard.CoverImage = CoverImage;

const StyledToolPlaceholder = styled.div`
    height: 266px;
    min-height: 266px;
    width: var(--tool-width);
    margin: var(--half-of-grid-gap) auto;

    .tool {
        height: 100%;
        width: var(--tool-width);
        background-color: #fff;
        box-shadow: 0px 0px 0px 1px var(--light-gray-100);
        border-radius: 4px;
        padding-block-start: 30px;
        transition: all 0.25s ease-in;

        &:has(~ &:hover) {
            // opacity: 0.5;
            background-color: blue;
        }

        & .details {
            text-align: center;
            color: var(--color-text-dark);
            padding-inline: 10px;
            margin-block-start: 10px;
        }
        & .details p {
            margin: 0;
            text-overflow: ellipsis;
            overflow-x: hidden;
        }
        & .toolName {
            font-size: 14px;
            line-height: 17.92px;
            font-weight: 500;
        }
        & .author {
            font-size: 13px;
            line-height: 16.64px;
            font-weight: 300;
        }
        & .thumbnail {
            display: flex;
            justify-content: center;
        }
    }

    .tool:hover {
        cursor: pointer;
        box-shadow: 0px 1px 5px 2px var(--light-gray-100);
        transform: scale(1.1);
        transition: transform 0.25s ease-in;
    }

    a {
        text-decoration: none;
    }

    @media screen and (min-width: 768px) {
        width: var(--placholder-width);
        padding: var(--half-of-grid-gap);
        margin: unset;
    }
`;

const CoverImageWrapper = styled.div`
    width: 100px;
    height: 75px;

    img {
        width: 100px;
        height: 75px;
    }
`;

export default ToolCard;
