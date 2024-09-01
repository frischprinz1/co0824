import styled from "styled-components";
import { Link } from "react-router-dom";

const GlobalHeader = () => {
    return (
        <StyledGlobalHeader>
            <div className="wrapper">
                <GlobalHeader.Icon wordMark={"ToolDepot"} />
            </div>
        </StyledGlobalHeader>
    );
};

const Icon = ({ wordMark }) => {
    return (
        <LogoWrapper>
            <Link to="/">
                <svg
                    role="link"
                    className="icon"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                >
                    <g id="Vector">
                        <path
                            fillRule="evenodd"
                            clipRule="evenodd"
                            d="M9.4221 2.0187C7.06027 -0.274701 3.231 -0.274701 0.869171 2.0187L11.7979 12.6308C14.1598 14.9242 17.989 14.9242 20.3509 12.6308L9.4221 2.0187Z"
                            fill="#306ADB"
                        />
                        <path
                            fillRule="evenodd"
                            clipRule="evenodd"
                            d="M19.3508 2.0187C16.989 -0.274701 13.1597 -0.274701 10.7979 2.0187L21.7266 12.6308C24.0885 14.9242 27.9177 14.9242 30.2796 12.6308L19.3508 2.0187Z"
                            fill="#f00"
                        />
                    </g>
                </svg>
                <span
                    role="word mark"
                    className="word-mark"
                >
                    {wordMark}
                </span>
            </Link>
        </LogoWrapper>
    );
};

GlobalHeader.Icon = Icon;
const StyledGlobalHeader = styled.header`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 87px;
    background-color: #fff;
    box-shadow: 0px 0px 4px 2px #aaa;

    .wrapper {
        width: var(--page-width-sm);
        padding-inline: var(--global-padding);
    }

    @media screen and (min-width: 768px) {
        .wrapper {
            width: var(--page-width);
        }
    }
`;

const LogoWrapper = styled.div`
    & .icon {
        margin-inline-end: 8px;
        width: 30px;
        height: 15px;
    }
    & .word-mark {
        font-size: 28px;
        font-weight: 700;
        line-height: 35.84px;
        color: #315351;
    }
    a {
        text-decoration: none;
    }
`;

export default GlobalHeader;
