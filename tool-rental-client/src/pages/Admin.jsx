import styled from "styled-components";
import GlobalHeader from "../components/GlobalHeader";
import { Link } from "react-router-dom";
import Button from "../components/Button";

const Admin = () => {
    return (
        <>
            <GlobalHeader />

            <LayoutWrapper>
                <div>
                    <p className="title">Uh oh... 404!</p>
                </div>
                <figure>
                    <blockquote>
                        <p>Why does the Java Programmer wear glasses?</p>
                    </blockquote>
                    <figcaption>
                        Because he can&#39;t C#. I know it&#39;s a lazy joke but
                        alas, I can no longer think #. I&#39;ve run out of brain
                        power for the next few hours!
                    </figcaption>
                </figure>

                <Link to="/">
                    <Button>Go back Home</Button>
                </Link>
            </LayoutWrapper>
        </>
    );
};

const LayoutWrapper = styled.div`
    width: var(--page-width);
    padding-inline: 200px;
    margin-block-start: 1rem;

    button {
        margin-block-start: 30px;
    }

    hr {
        border: 1px solid var(--light-gray-100);
        margin-bottom: 30px;
    }
    .title {
        font-size: 48px;
    }

    @media screen and (min-width: 768px) {
        width: var(--page-width);
        margin-block-start: 40px;
    }
`;
export default Admin;
