import styled from "styled-components";
import { Link } from "react-router-dom";
import ToolCard from "./ToolCard";
import { ToolsContext } from "../components/Contexts";
import { useContext } from "react";

const AvailableTools = () => {
    const tools = useContext(ToolsContext);

    return (
        <StyledAvailableTools>
            {tools?.length > 0 ? (
                tools.map((tool) => (
                    <Link
                        to={`${tool.toolCode}`}
                        key={tool.toolCode}
                        state={{ fromGrid: true }}
                    >
                        <ToolCard code={tool.toolCode} />
                    </Link>
                ))
            ) : (
                <>No tools found!</>
            )}
        </StyledAvailableTools>
    );
};

const StyledAvailableTools = styled.section`
    display: block;
    width: var(--page-width-sm);
    margin-block-start: 7.5px;

    a {
        text-decoration: none;
    }

    @media screen and (min-width: 768px) {
        display: flex;
        flex-wrap: wrap;
        width: var(--page-width);
    }
`;

export default AvailableTools;
